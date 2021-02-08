package com.ozgur.giys.api.ldap;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entry(
    base = "ou=agents", 
    objectClasses = { "organizationalUnit", "top" })
final public class PardusDeviceGroup {

    @Id
    private Name dn;

    private @Attribute(name = "ou") String label;
    private Boolean leaf;

    public String getDn(){
        return this.dn.toString();
    }


    
}
