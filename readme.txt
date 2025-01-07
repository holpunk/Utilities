Creating certificates for localhost or other servers

This is a simple script to create a self-signed certificate for localhost or any other server. It will create a certificate and a key file in the current directory. The certificate will be valid for 365 days.

    keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 365 -keysize 2048

create certificate from keystore in order to send request to server:

    keytool -export -alias youralias -keystore path/to/your/keystore.jks -file path/to/your/certificate.crt