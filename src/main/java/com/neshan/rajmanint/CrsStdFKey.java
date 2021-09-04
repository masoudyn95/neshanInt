package com.neshan.rajmanint;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CrsStdFKey implements Serializable {

    @Column(name = "st_id")
    private long student_id;

    @Column(name="crs_id")
    private long course_id;
}
