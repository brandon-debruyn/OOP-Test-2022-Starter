package ie.tudublin;

import processing.data.TableRow;

public class Nematode {
    private String nematodeName;
    private int length;
    private boolean limbs;
    private String gender;
    private boolean eyes;

    public Nematode(TableRow tr) {
        this(
            tr.getString("name"),
            tr.getInt("length"),
            tr.getInt("limbs") == 1,
            tr.getString("gender"),
            tr.getInt("eyes") == 1
            );
    }

    public Nematode(String nematodeName, int length, boolean limbs, String gender, boolean eyes) {
        this.nematodeName = nematodeName;
        this.length = length;
        this.limbs = limbs;
        this.gender = gender;
        this.eyes = eyes;
    }

    public String getNematodeName() {
        return nematodeName;
    }

    public void setNematodeName(String nematodeName) {
        this.nematodeName = nematodeName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isLimbs() {
        return limbs;
    }

    public void setLimbs(boolean limbs) {
        this.limbs = limbs;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEyes() {
        return eyes;
    }

    public void setEyes(boolean eyes) {
        this.eyes = eyes;
    }

    @Override
    public String toString() {
        return "Nematode [eyes=" + eyes + ", gender=" + gender + ", length=" + length + ", limbs=" + limbs
                + ", nematodeName=" + nematodeName + "]";
    }

}
