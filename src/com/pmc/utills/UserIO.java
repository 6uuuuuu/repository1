package com.pmc.utills;

import com.pmc.bean.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserIO {
    private static List<User> users = new ArrayList<>();
    private static final String USER_FILE = "user.obj";

    //写入用户信息
    public void writeUsers(User user) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE));
            oos.writeObject(users);
            oos.close();
        } catch (IOException e) {
            throw new BusinessException("io.write.error");
        }
    }

    //读取用户信息
    public void readUsers() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE));
            users = (List<User>) ois.readObject();//users为类的静态变量，因此该方法不需要返回一个List<User>的值
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new BusinessException("io.read.error");
        }
    }

    //添加用户信息
    public void addUsers(User user) {
        user.setId(users.size() + 1);//设置用户Id每次加一
        users.add(user);//每次新注册一个用户后，将该用户的信息加入users这个List中
    }

    //查找用户信息
    public boolean findUsers(User user) {
        for (User u : users) {
            if ((u.getUsername().equals(user.getUsername()))&&(u.getPassword().equals(user.getPassword()))) {
                return true;
            }
        }return false;
    }


}
