package SystemParts;

import Users.User;

public class Request {
    private User sender;
    private String content;
    private boolean isSigned; 
    private String signedBy; 

    public Request(User sender, String content) {
        this.sender = sender;
        this.content = content;
        this.isSigned = false;  
        this.signedBy = null;
    }

    public void signRequest(User user) {
        if (user instanceof Dean) {
            this.isSigned = true;
            this.signedBy = user.getName();
            System.out.println("Request signed by: " + user.getName());
        } else {
            System.out.println("User " + user.getName() + " is not authorized to sign the request.");
        }
    }

    public boolean isSigned() {
        return isSigned;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public String getContent() {
        return content;
    }

    public User getSender() {
        return sender;
    }
}

