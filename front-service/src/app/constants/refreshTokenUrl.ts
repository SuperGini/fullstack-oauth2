export const refreshTokenUrl = () => {
  const authServerUri = "http://localhost:8080/oauth2/token";
  const grantType = "refresh_token";
  const refreshTokenValue = sessionStorage.getItem("refresh_token");


  return `${authServerUri}?grant_type=${grantType}&refresh_token=${refreshTokenValue}`;
}
