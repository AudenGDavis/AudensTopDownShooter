package com.audentest.SupportClasses.GameClasses;

import com.audentest.SupportClasses.PhysicsClasses.LineCollider;
import com.audentest.SupportClasses.PhysicsClasses.Vector2;

public class Wall {
    private Vector2 start;
    private Vector2 end;

    public Wall(Vector2 Start, Vector2 End)
    {
        start = Start;
        end = End;
    }

    public synchronized Vector2 getStart() {
        return start;
    }
    public synchronized void setStart(Vector2 start) {
        this.start = start;
    }
    public synchronized Vector2 getEnd() {
        return end;
    }
    public synchronized void setEnd(Vector2 end) {
        this.end = end;
    }

    public LineCollider getCollider(){
        return new LineCollider(start.getX(), start.getY(), end.getX(), end.getY());
    }
}

