const redirectUrl = () => {
  const uri: string = 'http://localhost:8083/authorized';
  const codeChallenge: string = `QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8`;
  const codeChallengeMethod: string = 'S256';
  const redirectUri: string = `${uri}&code_challenge=${codeChallenge}&code_challenge_method=${codeChallengeMethod}`;

  const authServerUri: string = 'http://localhost:8080/oauth2/authorize';
  const responseType: string = 'code';
  const clientId: string = 'client'
  const scope: string = 'openid';

  return `${authServerUri}?response_type=${responseType}&client_id=${clientId}&scope=${scope}&redirect_uri=${redirectUri}`;
}

export default redirectUrl;
