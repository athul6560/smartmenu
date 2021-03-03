package com.andria.qrscan.Model;

public class categoryModel {


    public String cname;
    public String image_url;

    public categoryModel(String cname, String image_url) {
        this.cname = cname;
        this.image_url = image_url;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj instanceof categoryModel) {
            categoryModel temp = (categoryModel) obj;
            if (this.cname.equals(temp.cname) && this.image_url.equals(temp.image_url))
                return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub

        return (this.cname.hashCode() + this.image_url.hashCode());
    }
}
