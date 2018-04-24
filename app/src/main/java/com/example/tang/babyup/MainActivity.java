package com.example.tang.babyup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //当act创建的时候，就调用onCreate
        super.onCreate(savedInstanceState);

        //初始化页面
        setContentView(R.layout.login_main);

        //找到要操作的控件,找到账号密码和输入button
        et_username = (EditText)findViewById(R.id.username_input);
        et_password = (EditText)findViewById(R.id.password_input);
        btn_login   = (Button)findViewById(R.id.loginbutton);

        //添加按钮点击事件
        btn_login.setOnClickListener(new LoginClickLister());

        //尝试获取上一次用户登陆时保存的账号密码
        String[] info = Utils.ReadInfo();

        //如果返回不为空，把账号密码传给输入框
        if (info != null){

            //显示已经保存的账号密码
            et_username.setText(info[0]);
            et_password.setText(info[1]);
        }
    }

    private class LoginClickLister implements View.OnClickListener{

        //按钮被点击的时候，调用
        @Override
        public void onClick(View v){

            //1获取内容,把内容转换成string类型
            String str_username = et_username.getText().toString().trim();
            String str_password = et_password.getText().toString().trim();

            //2判断内容
            //2.1为空判断2.2特殊字符判断（先写着，不做了）
            if (TextUtils.isEmpty(str_username)){
                //系统内部显示
                System.out.println("账号不能为空");
                //app上弹窗显示
                //Toast参数：当前activity，提示文字，展示时间
                Toast.makeText(MainActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(str_password)){
                System.out.println("密码不能为空");
                Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            }

            //3开始方法
            else {

                //3.1 保存账号密码在本地
                boolean SaveInfo = Utils.SaveInfo(str_username,str_password);
                if (SaveInfo){
                    Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();

                }

            }
        }
    }

}
