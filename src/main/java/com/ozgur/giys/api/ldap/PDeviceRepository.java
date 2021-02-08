package com.ozgur.giys.api.ldap;

import org.springframework.data.ldap.repository.LdapRepository;


public interface PDeviceRepository extends LdapRepository<PardusDevice> {

    // List<PardusDevice> findByHostname(String hostname);
}
