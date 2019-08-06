package com.aws.loginapp.metrics.config;


import com.amazonaws.auth.*;


public class CredentialProviderChain extends AWSCredentialsProviderChain{
    CredentialProviderChain() {
        super(new SysProps());
    }
}
