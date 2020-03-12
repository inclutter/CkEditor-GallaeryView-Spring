package com.nuriBlog0309.main;

public class BoardPaging {
    private int startPageNo; // ���� ������ ��ȣ
    private int endPageNo; // ������ ������
    private int countRecord; // ��ü ���ڵ� ����
    private int countPage; // ��ü ������ ����
    private int pageSize; // �� �������� �������� ���ڵ� ����
    private int currentPageNo; // ���� ������ ��ȣ
    private int pageBlock;


    public BoardPaging(int countRecord, int currentPageNo, int number){
        int pageSize;
        if(number == 3) {
        	pageSize=12;
        }else if(number == 4) {
        	pageSize=16;
        }else {
        	pageSize=20;
        }
    	this.pageBlock = 4;
        this.pageSize = pageSize;
        this.countRecord = countRecord;
        this.currentPageNo = currentPageNo;
        //���� �������� 6�϶� 5���������̴ϱ� 5�� ������ 0�� �ƴϸ�
        if(((int)(this.currentPageNo % 5)) != 0) {
            // ���� �������� 6/5=>1*5+1=>6�� �ȴ�.
            this.startPageNo = ((int)(this.currentPageNo/5))*5+1;
        } else {
            // ���� �������� 2�� ��� 2/5 =0
            // ���� �������� 1�� �����Ѵ�.
            this.startPageNo = ((int)(this.currentPageNo/5))*5+1;
        }
        this.endPageNo = this.startPageNo + this.pageBlock;
        this.countPage = (this.countRecord + (this.pageSize -1)) / this.pageSize;
    }


    public int getStartPageNo() {
        return startPageNo;
    }

    public void setStartPageNo(int startPageNo) {
        this.startPageNo = startPageNo;
    }

    public int getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(int endPageNo) {
        this.endPageNo = endPageNo;
    }

    public int getCountRecord() {
        return countRecord;
    }

    public void setCountRecord(int countRecord) {
        this.countRecord = countRecord;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageBlock() {
        return pageBlock;
    }

    public void setPageBlock(int pageBlock) {
        this.pageBlock = pageBlock;
    }
}
