package org.example.domain.support.ids.policy;


import org.example.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.Random;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/2/2023
 * @Copyright： levinforward@163.com
 */
@Component
public class ShortCode implements IIdGenerator {

    @Override
    public long nextId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year - 2020);
        stringBuilder.append(hour);
        stringBuilder.append(String.format("%02d", week));
        stringBuilder.append(day);
        stringBuilder.append(String.format("%02d", new Random().nextInt(100)));

        return Long.parseLong(stringBuilder.toString());
    }
}
