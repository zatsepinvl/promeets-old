/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.unc6.promeets.model.service.entity;

import java.util.List;
import ru.unc6.promeets.model.entity.Board;
import ru.unc6.promeets.model.entity.BoardPage;

/**
 *
 * @author MDay
 */
public interface BoardService extends BaseService<Board,Long>
{
    List<BoardPage> getAllBoardPagesByMeetId(long id);
}
