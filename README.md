## How to run the project

1. Clone Project
2. Set Enviornment Variables for Application.properties. Variables to be changed are as follows. They contain values from cloudflare access keys and datasource details:
   * spring.datasource.url
   * spring.datasource.password
   * spring.datasource.username
   * spring.datasource.driver-class-name
   * cloudflare.r2.access-key=${access_key}
   * cloudflare.r2.secret-key=${secret_key}
   * cloudflare.r2.account-id=${account_id}
   * cloudflare.r2.bucket=${bucket}
   * cloudflare.r2.public-url=${public_url}
3. Running Application should work!
