INSERT INTO CLIENT_CREDENTIALS(
    CLIENT_ID,
    CLIENT_SECRET,
    ACCESS_TOKEN_VALIDITY_SECONDS,
    REFRESH_TOKEN_VALIDITY_SECONDS,
    AUTHORIZED_GRANT_TYPES,
    RESOURCE_IDS,
    SCOPE
) VALUES (
    'school-management@client',
    'secret',
    30000,
    30000,
    'authorization_code,refresh_token,implicit,password,client_credentials',
    'restservice',
    'read,write'
)
