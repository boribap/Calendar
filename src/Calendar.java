import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

class CalendarFrame
{
    FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 3,5);
    GridLayout grid = new GridLayout(1,7);
    Calendar calendar = new Calendar();

    JPanel selectionPanel = new JPanel();
    JPanel labelPanel = new JPanel();
    JPanel dayPanel[] = new JPanel[6];

    JComboBox choiceYear = new JComboBox();
    JComboBox choiceMonth = new JComboBox();

    JLabel labelYear = new JLabel("년");
    JLabel labelMonth = new JLabel("월");

    JButton search = new JButton("검색");


}

/**
 *
 * 1. 해당하는 해의 1월 1일이 무슨 요일인지 계산하는 메서드
 * 1-1. 해당 년이 들어오면 1583년부터 해당년 전년까지 몇년인지 구함
 * 1-2. 해당년 전년까지의 윤년합을 구함
 * 1-3. 1583년까지의 윤년합 구함 (383)
 * 1-4. 2.3의 결과를 뺌 = 윤년
 * 1-5. 1의 결과에서 4의 결과뺌 = 평년
 * 1-6. 평년 *1 + 윤년 *2 의 결과를 7로 나눈 나머지를 보고 요일 계산
 *
 * 2. 해당 달의 시작 요일 구하는 메서드
 * 2-1. 2월이 들어오면 해당년의 1월 1일과 비교하여 날짜 차이를 구함
 * 2-2. 날짜 차이 + 1에서구한 요일을 숫자로 변환한것 을 7로 나눈 나머지를 구한다.
 * 2-3. 그 숫자를 보고 무슨 요일인지 정함
 *
 * 3. 해당 달이 끝나는 날짜를 구함
 * 3-1. 윤년인지 아닌지 확인
 * 3-2. 28,29,30,31 중에서 윤년과 달에 맞춰 선택
 *
 * 4. 출력하는 메서드
 * 4-1. 해당 년도와 해당 달 출력
 * 4-2. 해당 달이 끝나는 날짜만큼 for문으로 돌려서 날짜 찍기
 * 4-3. 1일이 시작하는 위치를 tab으로 지정하여 그 위치부터 날짜 찍기
 *
 */
public class Calendar {
    public boolean isLeapYear(int y)
    {
        if (y%4 == 0)
        {
            if (y%100 == 0)
            {
                if (y%400 == 0)
                {
                    return true;
                }
                return false;
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public int endDayOfMonth(int y, int m) {
        if (m == 4 || m == 6 || m == 9 || m == 11)
        {
            return 30;
        }
        else if (m == 2 && isLeapYear(y))
        {
            return 29;
        }
        else if (m == 2 && !isLeapYear(y))
        {
            return 28;
        }
        else
        {
            return 31;
        }
    }

    public int startDay(int y, int m)
    {
        int cnt;
        int leapSumB;
        int leapSumA  = 383;
        int leapYear;
        int normalYear;
        int day;

        cnt = (y-1) - 1583 + 1;
        leapSumB = ((y-1)/4) - ((y-1)/100) + ((y-1)/400);
        leapYear = leapSumB - leapSumA;
        normalYear = cnt - leapYear;

        day = ((normalYear) + (leapYear*2))%7;

        for (int i = 1; i < m; i ++)
        {
            day = day + endDayOfMonth(y,i);
        }

        day = day - 1;

        return day%7;
    }

    public void CalendarPrint(int y, int m)
    {
        System.out.println("일\t월\t화\t수\t목\t금\t토");
        for (int i = 0; i < startDay(y, m); i++) {
            System.out.print("\t");
        }
        for (int j = 1; j <= endDayOfMonth(y,m); j++)
        {
            System.out.print(j + "\t");
            if ((startDay(y,m) + j)%7 == 0)
            {
                System.out.println("");
            }
        }


    }

    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("년도를 입력하세요 : ");
        int year = keyboard.nextInt();
        System.out.println("월을 입력하세요 : ");
        int month = keyboard.nextInt();

        System.out.println(year + "년 " + month + "월 달력");

        Calendar cal = new Calendar();
        cal.CalendarPrint(year,month);
    }
}
