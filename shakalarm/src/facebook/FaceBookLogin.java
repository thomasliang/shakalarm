package facebook;

import java.util.List;

import android.app.Activity;
import android.app.Application;

public class FaceBookLogin extends Activity{
	private List<GraphUser> selectedUsers;

    public List<GraphUser> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<GraphUser> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }
}
