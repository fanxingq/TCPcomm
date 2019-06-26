package com.hust.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 * ����Ϊ���߳��࣬���ڷ����
 */
public class ServerThread implements Runnable {

    private Socket client = null;
    public ServerThread(Socket client){
        this.client = client;
    }

    //����ͨ��ϸ�ڵľ�̬������������Ҫ�Ƿ����̳߳ط������ĵ���
    public static void execute(Socket client){
        try{
            //��ȡSocket���������������ͻ��˷�������
            PrintStream out = new PrintStream(client.getOutputStream());
            //��ȡSocket�����������������մӿͻ��˷��͹���������
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));

            boolean flag =true;
            while(flag){
                //���մӿͻ��˷��͹���������
                String str =  buf.readLine();
                if(str == null || "".equals(str)){
                    flag = false;
                }else{
                    if("bye".equals(str)){
                        flag = false;
                    }else{
                        System.out.println(str);
                        //�����յ����ַ���ǰ�����echo�����͵���Ӧ�Ŀͻ���
                        out.println("echo:success!");

                      //����sql���

                    }
                }
            }



            out.close();
//            buf.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        execute(client);
        System.out.println("��ǰ�߳�"+Thread.currentThread().getName());     //�鿴��ǰʹ�õ��̣߳����ܻḴ��֮ǰ���ڵ�
    }

}