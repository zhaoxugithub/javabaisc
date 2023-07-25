package com.serendipity.learn.zuo.tanxin;

public class Code03_BestArrange1 {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //暴力解
    public static int bestArray1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    /**
     * 暴力解处理流程
     *
     * @param programs 剩下的会议数量
     * @param done     已经计算好的会议数量
     * @param timeLine 当前的时间线
     * @return 返回最多的会议数量
     */
    public static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            //如果会议开始时间大于结束时间
            if (programs[i].start >= timeLine) {
                //移除掉当前会议，并且返回新的会议列表
                Program[] programs1 = removePrograms(programs, i);
                max = Math.max(process(programs1, done + 1, programs[i].end), max);
            }
        }
        return max;
    }

    private static Program[] removePrograms(Program[] programs, int i) {
        Program[] newProgram = new Program[programs.length - 1];
        int k = 0;
        for (int i1 = 0; i1 < programs.length; i1++) {
            if (i1 != i) {
                newProgram[k++] = newProgram[i1];
            }
        }
        return newProgram;
    }

    public static void main(String[] args) {


    }
}
