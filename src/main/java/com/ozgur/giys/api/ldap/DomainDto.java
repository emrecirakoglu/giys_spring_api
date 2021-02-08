package com.ozgur.giys.api.ldap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainDto {

    private List<PardusDevice> pardusDevices;
    private List<PardusDeviceGroup> pardusDeviceGroups;
    
}
