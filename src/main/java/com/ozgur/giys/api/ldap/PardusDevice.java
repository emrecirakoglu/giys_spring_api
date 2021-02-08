package com.ozgur.giys.api.ldap;

import java.io.Serializable;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entry(
    base = "ou=agents", 
    objectClasses = { "device", "pardusDevice", "top" })
final public class PardusDevice implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private Name dn;
    private @Attribute(name = "cn") String label;
    private @Attribute(name = "routingKey") String routingKey;
    private Boolean isOnline;


    public String getDn() {
        return this.dn.toString();
    }

}
