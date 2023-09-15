import { useState } from "react";
import { UserDetails } from "../../ts/myPageComponentType";
import { SideContentComp } from "./sideComponent";
import { MainContentComp } from "./mainContentComponent";

export const MyPageComp: React.FC= () => {
  const [activeButton, setActiveButton] = useState<string | undefined>("userDetails");
  const [myDetail, setMyDetail] = useState<UserDetails>(window.userDetail);
  
  return (
    <>
      <SideContentComp setActiveButton={setActiveButton} myDetail={myDetail} />
      <MainContentComp activeButton={activeButton} myDetail={ myDetail } />
    </>
  )
}
