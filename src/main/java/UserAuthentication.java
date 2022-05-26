import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class UserAuthentication {

    private BufferedReader in = null;
    private BufferedWriter out = null;
    private String acl_file_path = "/home/felix/IdeaProjects/missing_items_app/src/main/resources/registered_users.txt";

    public void signupUser(String username, String password) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(this.acl_file_path, true));
        out.append(username + "," + password);
    }

    public boolean checkUserExists(String username, String password) throws IOException {
        List<String> users = Files.readAllLines(Path.of(this.acl_file_path));
        Boolean userExists = false;
        for (String user: users) {
            String[] dbUser = user.split(",");
            if (dbUser[0].equals(username.strip()) && dbUser[1].equals(password.strip())) {
                userExists = true;
                break;
            }
        }
        return userExists;
    }
}