const tokenUrl = (code: string): string => {

  const uri = 'http://localhost:8083/authorized'
  const grantType = 'authorization_code';
  const codeVerifier = 'qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI';
  const clientId = 'client';
  const redirectUri =`${uri}&grant_type=${grantType}&code=${code}&code_verifier=${codeVerifier}`;

  const authServerUri = 'http://localhost:8080/oauth2/token';
  return `${authServerUri}?client_id=${clientId}&redirect_uri=${redirectUri}`;
}

export default tokenUrl;
