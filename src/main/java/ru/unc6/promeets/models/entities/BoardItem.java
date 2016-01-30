package ru.unc6.promeets.models.entities;

import javax.persistence.*;

/**
 * Created by Vladimir on 30.01.2016.
 */
@Entity
@Table(name = "board_items", schema = "public", catalog = "promeets_db")
public class BoardItem {
    private long itemId;
    private String data;
    private BoardPage boardPage;
    private MeetTarget target;
    private File file;

    @Id
    @Column(name = "item_id", nullable = false)
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "data", nullable = true, length = -1)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardItem that = (BoardItem) o;

        if (itemId != that.itemId) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (itemId ^ (itemId >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "board_page_id", referencedColumnName = "page_id", nullable = false)
    public BoardPage getBoardPage() {
        return boardPage;
    }

    public void setBoardPage(BoardPage boardPage) {
        this.boardPage = boardPage;
    }

    @ManyToOne
    @JoinColumn(name = "target_id", referencedColumnName = "target_id")
    public MeetTarget getTarget() {
        return target;
    }

    public void setTarget(MeetTarget target) {
        this.target = target;
    }

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file_id")
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
