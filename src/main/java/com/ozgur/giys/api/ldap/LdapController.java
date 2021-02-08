package com.ozgur.giys.api.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.query.SearchScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.HashMap;
import java.util.List;

import com.ozgur.giys.api.messaging.RabbitmqManagementService;

@RestController
@RequestMapping("/api/domain")
public class LdapController {

        @Autowired
        private PDeviceRepository pDeviceRepository;

        @Autowired
        private PGroupRepository pGroupRepository;

        @Autowired
        private RabbitmqManagementService rabbitmqManagementService;

        @PostMapping(path = "/")
        public ResponseEntity<?> getAllDevices(@RequestBody HashMap<String, String> base) {
                SearchScope scope = SearchScope.ONELEVEL;

                List<PardusDevice> pDevices = (List<PardusDevice>) pDeviceRepository.findAll(query()
                                .base(base.get("dn")).searchScope(scope).where("objectClass").is("pardusDevice"));

                pDevices.forEach(device -> {
                        device.setIsOnline(this.rabbitmqManagementService.isOnline(device.getRoutingKey()));
                });

                List<PardusDeviceGroup> pDeviceGroups = (List<PardusDeviceGroup>) pGroupRepository.findAll(query()
                                .base(base.get("dn")).searchScope(scope).where("objectClass").is("organizationalUnit"));

                pDeviceGroups.forEach(pGroup -> {
                        Iterable<PardusDevice> oPardusDevice = pDeviceRepository.findAll(query().base(pGroup.getDn())
                                        .searchScope(scope).where("objectClass").is("pardusDevice"));
                        pGroup.setLeaf(!oPardusDevice.iterator().hasNext());
                });

                return ResponseEntity.ok().body(
                                DomainDto.builder().pardusDevices(pDevices).pardusDeviceGroups(pDeviceGroups).build());
        }

        @PostMapping(path = "/search")
        public ResponseEntity<?> searchDevice(@RequestBody HashMap<String, String> hostname) {
                List<PardusDevice> pDevices = (List<PardusDevice>) pDeviceRepository.findAll(query().base("ou=agents")
                                .searchScope(SearchScope.SUBTREE).where("objectClass").is("pardusDevice").and("cn")
                                .whitespaceWildcardsLike(hostname.get("hostname")));
                return ResponseEntity.ok().body(pDevices);
        }

        @PostMapping(path = "/root")
        public ResponseEntity<?> getRoot(@RequestBody HashMap<String, String> base) {

                List<PardusDeviceGroup> pDeviceGroups = (List<PardusDeviceGroup>) pGroupRepository.findAll(query()
                                .base(base.get("dn")).searchScope(SearchScope.OBJECT).where("objectClass").is("organizationalUnit"));

                pDeviceGroups.forEach(pGroup -> {
                        Iterable<PardusDevice> oPardusDevice = pDeviceRepository.findAll(query().base(pGroup.getDn())
                                        .searchScope(SearchScope.ONELEVEL).where("objectClass").is("pardusDevice"));
                        pGroup.setLeaf(!oPardusDevice.iterator().hasNext());
                });
                return ResponseEntity.ok().body(pDeviceGroups.get(0));
        }

}
