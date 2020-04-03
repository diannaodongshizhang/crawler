package com.example.demo.Service.task;

public class MathSalary {
	public static Integer[] getSalary(String salaryStr) {
		Integer[] salary =new Integer[2];
		String date =salaryStr.substring(salaryStr.length()-1,salaryStr.length());
		if(!"月".equals(date) && !"年".equals(date)) {
			salaryStr = salaryStr.substring(0,salaryStr.length()-2);
			salary[0] = salary[1] = str2Num(salaryStr,240);
			return salary;
		}
		//万or千
		String unit =salaryStr.substring(salaryStr.length()-3,salaryStr.length()-2);
		//？ - ？
		String[] salarys=salaryStr.substring(0,salaryStr.length()-3).split("-");
		salary[0] = mathSalary(date, unit, salarys[0]);
        salary[1] = mathSalary(date, unit, salarys[1]);

        return salary;
		
	}

	 //根据条件计算薪水
    private static Integer mathSalary(String date, String unit, String salaryStr) {
        Integer salary = 0;

        //判断单位是否是万
        if ("万".equals(unit)) {
            //如果是万，薪水乘以10000
            salary = str2Num(salaryStr, 10000);
        } else {
            //否则乘以1000
            salary = str2Num(salaryStr, 1000);
        }

        //判断时间是否是月
        if ("月".equals(date)) {
            //如果是月，薪水乘以12
            salary = str2Num(salary.toString(), 12);
        }

        return salary;
    }

    private static int str2Num(String salaryStr, int num) {
        try {
            // 把字符串转为小数，必须用Number接受，否则会有精度丢失的问题
            Number result = Float.parseFloat(salaryStr) * num;
            return result.intValue();
        } catch (Exception e) {
        }
        return 0;
    }

}
