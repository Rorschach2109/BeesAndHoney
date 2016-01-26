/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@TypeDef(
        name="encryptedPassword",
        typeClass=EncryptedStringType.class,
        parameters={
            @Parameter(name="encryptorRegisteredName",
                value="BCHibernatePasswordEncryptor")
        }
)

package com.beesandhoney.model;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Parameter;
import org.jasypt.hibernate4.type.EncryptedStringType;