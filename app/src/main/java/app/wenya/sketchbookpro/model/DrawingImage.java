package app.wenya.sketchbookpro.model;


import org.joda.time.DateTime;

import java.io.Serializable;

import app.wenya.sketchbookpro.base.Constant;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 16:59
 */

public class DrawingImage implements Serializable {
    private String name;
    private String folder;
    private String date;
    public DrawingImage(){

    }

    public DrawingImage(String folder, String name) {
        this.folder = folder;
        this.name = name;
        this.date = new DateTime().toString(Constant.pattern);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
