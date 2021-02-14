package fr.virthia.utils.tchat;

public class TchatFormat {

    private TchatContentType[] tchatContentTypes = new TchatContentType[6];
    private int iterator = 0;

    public void setNext(TchatContentType tchatContentType){
        tchatContentTypes[iterator]=tchatContentType;
        iterator++;
    }

    public String generate(String message, String tag, String playerName, String otherOne, String otherTwo, String otherThree){
        StringBuilder strBuilder = new StringBuilder("");
        for(TchatContentType tchatContentType : tchatContentTypes) {
            if (tchatContentType == null) break;
            switch (tchatContentType) {
                case MESSAGE:
                    strBuilder.append(message + " ");
                    break;
                case TAG:
                    strBuilder.append(tag + " ");
                    break;
                case PLAYER:
                    strBuilder.append(playerName + " ");
                    break;
                case OTHER_1:
                    strBuilder.append(otherOne + " ");
                    break;
                case OTHER_2:
                    strBuilder.append(otherTwo + " ");
                    break;
                case OTHER_3:
                    strBuilder.append(otherThree + " ");
                    break;
                default:
                    return strBuilder.toString();
            }
        }
        return strBuilder.toString();
    }
}
