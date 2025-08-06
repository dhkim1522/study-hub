These REST controllers expose the following endpoints:


For `CaCertEntity` (`/api/ca-certs`):
* POST /api/ca-certs: Create a new CA certificate.
* GET /api/ca-certs/{id}: Retrieve a CA certificate by ID.
* GET /api/ca-certs: Retrieve all CA certificates.
* GET /api/ca-certs/by-path-length: Retrieve CA certificates by path length.
* GET /api/ca-certs/valid-last-issued-after: Retrieve valid and last issued CA certificates by path length and notAfter
  greater than or equal to a given date.
* GET /api/ca-certs/valid-last-issued-before: Retrieve valid and last issued CA certificates by path length and notAfter
  less than or equal to a given date.
* PUT /api/ca-certs/{id}: Update an existing CA certificate.
* DELETE /api/ca-certs/{id}: Delete a CA certificate by ID.


For `LeafCertEntity` (`/api/leaf-certs`):
* POST /api/leaf-certs: Create a new leaf certificate.
* GET /api/leaf-certs/{id}: Retrieve a leaf certificate by ID.
* GET /api/leaf-certs: Retrieve all leaf certificates.
* PUT /api/leaf-certs/{id}: Update an existing leaf certificate.
* DELETE /api/leaf-certs/{id}: Delete a leaf certificate by ID.


For `PolicyEntity` (`/api/policies`):
* POST /api/policies: Create a new policy.
* GET /api/policies/{id}: Retrieve a policy by ID.
* GET /api/policies: Retrieve all policies.
* PUT /api/policies/{id}: Update an existing policy.
* DELETE /api/policies/{id}: Delete a policy by ID.