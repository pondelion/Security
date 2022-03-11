import React, { useEffect, useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import axios from "axios";

import { config } from "../Config";


const UserProfile = () => {
  const { user, isAuthenticated, isLoading, getAccessTokenSilently } = useAuth0();
  const [ roles, setRoles ] = useState<any>("");

  if (isLoading) {
    return <div>Loading ...</div>;
  }

  useEffect(() => {
    (async () => {
      const token = await getAccessTokenSilently({
        audience: `https://${config.DOMAIN}/api/v2/`,
        scope: "read:current_user",
      })
      console.log(token);
      // const userDetailsByIdUrl = `https://${config.DOMAIN}/api/v2/users/${user!.sub}`;
      // console.log(userDetailsByIdUrl);

      // const metadataResponse = await fetch(userDetailsByIdUrl, {
      //   headers: {
      //     Authorization: `Bearer ${token}`,
      //   },
      // });
      // console.log(metadataResponse);

      // const { user_metadata } = await metadataResponse.json();
      // console.log(user_metadata);

      console.log(user);

      const roleResponse = await fetch("http://127.0.0.1:8000/role", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      axios
        .get(
          "http://127.0.0.1:8000/role",
          {
            headers: {
            Authorization: `Bearer ${token}`,
            }
          }
        )
        .then((response) => {
          console.log(response.data);
          setRoles(JSON.stringify(response.data[0]));
        })
        .catch((e) => {
          console.log(e);
        })
    })();
  }, [getAccessTokenSilently, user?.sub]);

  return isAuthenticated ?
    (
        <div>
          <img src={user!.picture} alt={user!.name} />
          <h2>{user!.name}</h2>
          <p>{user!.email}</p>
          {roles}
        </div>
    )
    :
    (
      <div></div>
    )
};

export default UserProfile;