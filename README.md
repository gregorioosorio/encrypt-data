# encrypt-data
Spring Boot: how to encrypt/decrypt data using a RSA key pair 
## Generate key files using openssl
1. key pair
`openssl genrsa -out private_key.pem 2048`
1. public key
`openssl rsa -in private_key.pem -pubout -outform DER -out tst_public.der`
1. private key
`openssl pkcs8 -topk8 -inform PEM -outform DER -in private_key.pem -out private_key.der -nocrypt`
