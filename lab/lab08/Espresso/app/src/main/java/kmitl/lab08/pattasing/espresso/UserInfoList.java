package kmitl.lab08.pattasing.espresso;

import java.util.List;

public class UserInfoList {
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public void clearList(){
        this.userInfoList.clear();
    }

    private List<UserInfo> userInfoList;
}
