package com.asiainfo.grid.BaseTest;

import com.asiainfo.grid.sop.manage.atom.zg.impl.ZgBaseStationDetailInfoAtomSVImpl;
import com.asiainfo.grid.sop.manage.atom.zg.interfaces.IZgBaseStationDetailInfoAtomSV;
import com.asiainfo.grid.sop.manage.controller.zg.api.ZgBaseStationDetailInfoController;
import com.asiainfo.grid.sop.manage.controller.zg.vo.ZgGroupResourcesVO;
import com.asiainfo.grid.sop.manage.dao.mapper.gsop.bo.ZgBaseStationDetailInfo;
import com.asiainfo.grid.sop.manage.dao.mapper.gsop.bo.ZgChnlInfoNormalSop;
import com.asiainfo.grid.sop.manage.dao.mapper.gsop.bo.ZgCommunityCellInfo;
import com.asiainfo.grid.sop.manage.dao.mapper.gsop.bo.ZgGroupResources;
import com.asiainfo.grid.sop.manage.service.gsop.impl.ZgBaseStationDetailInfoServiceImpl;
import com.asiainfo.grid.sop.manage.service.gsop.interfaces.IZgBaseStationDetailInfoService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CodeTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CodeTest.class);
  private static final String CHARSET_NAME = "UTF-8";
  private static final String FROM_NAME = ZgBaseStationDetailInfo.class.getSimpleName();//模板的实体
  private static final Class templateAtomSV = IZgBaseStationDetailInfoAtomSV.class;
  private static final Class templateService = IZgBaseStationDetailInfoService.class;//模板类
  private static final Class templateServiceImpl = ZgBaseStationDetailInfoServiceImpl.class;//模板类
  private static final Class templateController = ZgBaseStationDetailInfoController.class;//模板类

  private static final Class templateAtomSVImpl = ZgBaseStationDetailInfoAtomSVImpl.class;//模板类
  private static final String PROJECT_PATH = "C:\\1work\\idea2020\\coms-chnl-grid-sop\\coms-chnl-grid-sop-manage\\src\\main\\java";
  List<Class<?>> list = new ArrayList<>();

  @Before
  public void init() {
    list.add(ZgChnlInfoNormalSop.class);
    list.add(ZgCommunityCellInfo.class);
    list.add(ZgGroupResources.class);
  }

  @Test
  public void all() {
    VO();
    Request();
    AutoSV();
    AtomSVImpl();
    Service();
    ServiceImpl();
    Controller();
  }

  /**
   * @description: 生成原子层 AutoSV
   * @author: youpd@asiainfo.com
   * @date: 2020-04-14 15:14
   */
  @Test
  public void AutoSV() {
    for (Class cls : list) {
      String simpleName = cls.getSimpleName();
      String path = templateAtomSV.getPackage().getName().replace(".", File.separator);
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String filePath = PROJECT_PATH + File.separator + path;
        String oldFileName = templateAtomSV.getSimpleName() + ".java";
        File oldFile = new File(filePath + File.separator + oldFileName);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, CHARSET_NAME));
        String newFileName = filePath + File.separator + oldFileName.replace(FROM_NAME, simpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), CHARSET_NAME));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(FROM_NAME, simpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }

  @Test
  public void AtomSVImpl() {
    for (Class cls : list) {
      String simpleName = cls.getSimpleName();
      String path = templateAtomSVImpl.getPackage().getName().replace(".", File.separator);
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String filePath = PROJECT_PATH + File.separator + path;
        String oldFileName = templateAtomSVImpl.getSimpleName() + ".java";
        File oldFile = new File(filePath + File.separator + oldFileName);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, CHARSET_NAME));
        String newFileName = filePath + File.separator + oldFileName.replace(FROM_NAME, simpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), CHARSET_NAME));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(FROM_NAME, simpleName);
          if (tempString.contains("//build_criteria")) {
            writer.write(tempString);
            writer.newLine();
            StringBuilder stringBuilder = new StringBuilder();
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
              if (field.getGenericType().toString().equals("class java.util.Date")) {
                continue;
              } else {
                String name = field.getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String str = "if (!ObjectUtils.isEmpty(obj.get" + name + "())) { criteria.and" + name + "EqualTo(obj.get" + name + "());}";
                stringBuilder.append(str);
              }
            }
            LOGGER.error(":{}", stringBuilder.toString());
            writer.write(stringBuilder.toString());
            writer.newLine();
          } else {
            writer.write(tempString);
            writer.newLine();
          }

        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * @description: 复制VO
   * @author: youpd@asiainfo.com
   * @date: 2020-04-14 16:50
   */
  @Test
  public void VO() {
    String voPath = "com.asiainfo.grid.sop.manage.controller.zg.vo";
    for (Class cls : list) {
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String simpleName = cls.getSimpleName();
        File oldFile = new File(PROJECT_PATH + File.separator
            + cls.getPackage().getName().replace(".", File.separator)
            + File.separator + simpleName + ".java");
        if (!oldFile.exists()) {
          continue;
        }
        File newFile = new File(PROJECT_PATH + File.separator + voPath.replace(".", File.separator) + File.separator + simpleName + "VO.java");
        if (newFile.exists()) {
          newFile.delete();
        }
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, CHARSET_NAME));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), CHARSET_NAME));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          if (tempString.startsWith("package ")) {
            writer.write("package " + voPath + ";");
          } else if (tempString.startsWith("public class ")) {
            writer.write("public class " + simpleName + "VO {");
          } else {
            writer.write(tempString);
          }
          writer.newLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * @description: 复制Request
   * @date: 2020-07-13 15:30
   */
  @Test
  public void Request() {
    String voPath = "com.asiainfo.grid.sop.manage.controller.zg.params";
    for (Class cls : list) {
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String simpleName = cls.getSimpleName();
        File oldFile = new File(PROJECT_PATH + File.separator
            + cls.getPackage().getName().replace(".", File.separator)
            + File.separator + simpleName + ".java");
        if (!oldFile.exists()) {
          continue;
        }
        File newFile = new File(PROJECT_PATH + File.separator + voPath.replace(".", File.separator) + File.separator + "Query" + simpleName + "Request.java");
        if (newFile.exists()) {
          newFile.delete();
        }
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, CHARSET_NAME));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), CHARSET_NAME));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          if (tempString.startsWith("package ")) {
            writer.write("package " + voPath + ";");
          } else if (tempString.startsWith("public class ")) {
            writer.write("public class " + "Query" + simpleName + "Request {");
          } else if (tempString.equals("}")) {
            writer.write("private String orderByClause;\n"
                + "\n"
                + "  public String getOrderByClause() {\n"
                + "    return orderByClause;\n"
                + "  }\n"
                + "\n"
                + "  public void setOrderByClause(String orderByClause) {\n"
                + "    this.orderByClause = orderByClause;\n"
                + "  }"
                + "}"
            );
          } else {
            writer.write(tempString);
          }
          writer.newLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }


  @Test
  public void Service() {
    for (Class cls : list) {
      String simpleName = cls.getSimpleName();
      String path = templateService.getPackage().getName().replace(".", File.separator);
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String filePath = PROJECT_PATH + File.separator + path;
        String oldFileName = templateService.getSimpleName() + ".java";
        File oldFile = new File(filePath + File.separator + oldFileName);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, CHARSET_NAME));
        String newFileName = filePath + File.separator + oldFileName.replace(FROM_NAME, simpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), CHARSET_NAME));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(FROM_NAME, simpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Test
  public void ServiceImpl() {
    for (Class cls : list) {
      String simpleName = cls.getSimpleName();
      String path = templateServiceImpl.getPackage().getName().replace(".", File.separator);
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String filePath = PROJECT_PATH + File.separator + path;
        String oldFileName = templateServiceImpl.getSimpleName() + ".java";
        File oldFile = new File(filePath + File.separator + oldFileName);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, CHARSET_NAME));
        String newFileName = filePath + File.separator + oldFileName.replace(FROM_NAME, simpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), CHARSET_NAME));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(FROM_NAME, simpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Test
  public void Controller() {
    for (Class cls : list) {
      String simpleName = cls.getSimpleName();
      String path = templateController.getPackage().getName().replace(".", File.separator);
      BufferedWriter writer = null;
      BufferedReader reader = null;
      try {
        String filePath = PROJECT_PATH + File.separator + path;
        String oldFileName = templateController.getSimpleName() + ".java";
        File oldFile = new File(filePath + File.separator + oldFileName);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        reader = new BufferedReader(new InputStreamReader(fileInputStream, CHARSET_NAME));
        String newFileName = filePath + File.separator + oldFileName.replace(FROM_NAME, simpleName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
          newFile.delete();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), CHARSET_NAME));
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
          tempString = tempString.replaceAll(FROM_NAME, simpleName);
          writer.write(tempString);
          writer.newLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          reader.close();
          writer.flush();
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Test
  public void build_criteria() {
    Class cls = ZgBaseStationDetailInfo.class;
    //生成代码
    StringBuilder stringBuilder = new StringBuilder();
    Field[] declaredFields = cls.getDeclaredFields();
    for (Field field : declaredFields) {
      if (field.getGenericType().toString().equals("class java.util.Date")) {
        continue;
      } else {
        String name = field.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        String str = "if (!ObjectUtils.isEmpty(obj.get" + name + "())) { criteria.and" + name + "EqualTo(obj.get" + name + "());}";
        stringBuilder.append(str);
      }
    }
    LOGGER.error(":{}", stringBuilder.toString());
  }

  @Test
  public void translateVo() {
    Class cls = ZgGroupResourcesVO.class;
    //生成代码
    StringBuilder stringBuilder = new StringBuilder();
    Field[] declaredFields = cls.getDeclaredFields();
    for (Field field : declaredFields) {
      if (field.getGenericType().toString().equals("class java.util.Date")) {
        continue;
      } else {
        String name = field.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        String str = "if (ObjectUtils.isEmpty(vo.get" + name + "())) { vo.set" + name + "("  + " \"-\");}";
        stringBuilder.append(str);
      }
    }
    LOGGER.error(":{}", stringBuilder.toString());
  }
}
