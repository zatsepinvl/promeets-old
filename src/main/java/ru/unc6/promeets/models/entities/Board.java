package ru.unc6.promeets.models.entities;

import javax.persistence.*;

/**
 * Created by Vladimir on 30.01.2016.
 */
@Entity
@Table(name = "boards")
public class Board {
    private long boardId;
    private String name;
    private Meet meet;

    @Id
    @Column(name = "board_id", nullable = false)
    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (boardId != board.boardId) return false;
        if (name != null ? !name.equals(board.name) : board.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (boardId ^ (boardId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "board")
    public Meet getMeet() {
        return meet;
    }

    public void setMeet(Meet meet) {
        this.meet = meet;
    }
}
