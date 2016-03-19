package br.edu.fa7.gestaopessoas.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by clairtonluz<clairton.c.l@gmail.com> on 19/03/16.
 */
public abstract class DataUtil {

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
