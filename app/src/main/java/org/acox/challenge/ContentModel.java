package org.acox.challenge;

public class ContentModel {
    private String title;
    private String content;
    private String msgTime;
    private String img;

public ContentModel(String title, String content, String msgTime, String img){
    this.title = title;
    this.content = content;
    this.msgTime = msgTime;
    this.img = img;
}

public String getTitle(){
    return title;
}

public void setTitle(){
    this.title = title;
}
    public String getContent(){
        return content;
    }

    public void setContent(){
        this.content = content;
    }

    public String getMsgTime(){
        return msgTime;
    }

    public void setMsgTime(){
        this.msgTime = msgTime;
    }

    public String getImg(){
        return img;
    }

    public void setImg(){
        this.img = img;
    }
}