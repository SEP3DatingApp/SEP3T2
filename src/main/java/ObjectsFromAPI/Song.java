package ObjectsFromAPI;

public class Song {
    public int songID,voteAmount,categoryID;
    public String title,artist;

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getVoteAmount() {
        return voteAmount;
    }

    public void setVoteAmount(int voteAmount) {
        this.voteAmount = voteAmount;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songID=" + songID +
                ", voteAmount=" + voteAmount +
                ", categoryID=" + categoryID +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
