import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5 import uic

# UI파일 연결
# 단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.

form_class = uic.loadUiType("FolderMaker.ui")[0]
sub_ui = uic.loadUiType("SubDialog.ui")[0]

fileList = []


class SubWindowClass(QMainWindow, sub_ui):
    def __init__(self):
        super().__init__()
        self.setupUi(self)

# 화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)

        self.checkBox_2.toggle()
        self.checkBox_2.setEnabled(False)
        self.checkBox_3.toggle()

        for i in range(1, 101):
            self.comboBox_1.addItem(str(i))
            self.comboBox_2.addItem(str(i))


        self.pushButton_1.clicked.connect(self.fileSaveAddress)
        self.pushButton_2.clicked.connect(self.folderMaker)
        self.pushButton_3.clicked.connect(self.buttonClickEvent)

        self.checkBox_1.clicked.connect(self.CheckBoxState_1)
        self.checkBox_2.clicked.connect(self.CheckBoxState_2)
        # self.checkBox_3.clicked.connect(self.CheckBoxState_3)

    def fileSaveAddress(self):
        address = QFileDialog.getExistingDirectory(self, "Find Folder")
        self.lineEdit_1.setText(address)
        # print(address)

    def folderMaker(self):
        import os
        global fileList
        for i in fileList:
            os.mkdir(i)

        fileList.clear()
        self.listWidget.clear()
        self.lineEdit_5.clear()
        # 완료 다이얼로그 생성
        sub = SubWindowClass()
        sub.exec_()



    def buttonClickEvent(self):
        global fileList
        fileList.clear()
        self.listWidget.clear()

        name = self.lineEdit_4.text()
        address = self.lineEdit_1.text()
        start_str = self.comboBox_1.currentText()
        stop_str = self.comboBox_2.currentText()

        if self.checkBox_1.isChecked():
            if self.checkBox_3.isChecked():
                self.lineEdit_5.setText(start_str+" "+name)
                for i in range(int(start_str), int(stop_str) + 1):
                    fileList.append(address + "/"+"%03d " % i + name)
            else:
                self.lineEdit_5.setText(start_str+""+name)
                for i in range(int(start_str), int(stop_str)+1):
                    fileList.append(address + "/"+"%03d" % i + name)

        if self.checkBox_2.isChecked():
            if self.checkBox_3.isChecked():
                self.lineEdit_5.setText(name+" "+start_str)
                for i in range(int(start_str), int(stop_str)+1):
                    fileList.append(address + "/" + name+" %03d" % i)
            else:
                self.lineEdit_5.setText(name+""+start_str)
                for i in range(int(start_str), int(stop_str)+1):
                    fileList.append(address + "/" + name+"%03d" % i)


        for i in fileList:
            self.listWidget.addItem(i)


    def CheckBoxState_1(self):
        if self.checkBox_1.isChecked():
            self.checkBox_1.setEnabled(False)
            self.checkBox_2.toggle()
            self.checkBox_2.setEnabled(True)

    def CheckBoxState_2(self):
        if self.checkBox_2.isChecked():
            self.checkBox_1.setEnabled(True)
            self.checkBox_1.toggle()
            self.checkBox_2.setEnabled(False)






if __name__ == "__main__":
    # QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv)

    # WindowClass의 인스턴스 생성
    myWindow = WindowClass()

    # 프로그램 화면을 보여주는 코드
    myWindow.show()

    # 프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()
