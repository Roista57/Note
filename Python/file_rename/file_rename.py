import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5 import uic

# UI파일 연결
# 단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.

total = 0
fileList = []

form_class = uic.loadUiType("MainWindow.ui")[0]


# 화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pushButton.clicked.connect(self.buttonClickEvent)
        self.pushButton_2.clicked.connect(self.buttonClickClear)
        self.pushButton_3.clicked.connect(self.fileSaveAddress)

    def buttonClickEvent(self):
        import natsort
        global total
        global fileList

        self.tableWidget.setColumnCount(2)
        self.tableWidget.horizontalHeader().setSectionResizeMode(QHeaderView.Stretch)
        self.tableWidget.setHorizontalHeaderLabels(['기존 파일', '변환 파일'])

        name = QFileDialog.getOpenFileNames(self, "Open file", "./")
        name_list = list(name[0])
        total += len(name_list)

        for i in name_list:
            fileList.append(i)

        fileList = natsort.natsorted(fileList)
        self.tableWidget.clear()
        self.tableWidget.setHorizontalHeaderLabels(['기존 파일', '변환 파일'])
        self.tableWidget.setRowCount(total)
        count = 0
        for i in fileList:
            self.tableWidget.setItem(count, 0, QTableWidgetItem(i))
            count += 1

        self.MaxCount.setText(str(total) + " 개")

    def buttonClickClear(self):
        global total
        global fileList
        total = 0
        fileList.clear()

        self.tableWidget.clear()
        self.tableWidget.setHorizontalHeaderLabels(['기존 파일', '변환 파일'])
        self.tableWidget.setRowCount(total)
        self.MaxCount.setText(str(total) + " 개")

    def fileSaveAddress(self):
        address = QFileDialog.getExistingDirectory(self, "Find Folder")
        self.lineEdit.setText(address)
        print(address)


if __name__ == "__main__":
    # QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv)

    # WindowClass의 인스턴스 생성
    myWindow = WindowClass()

    # 프로그램 화면을 보여주는 코드
    myWindow.show()

    # 프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()
