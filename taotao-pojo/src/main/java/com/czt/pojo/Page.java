package com.czt.pojo;

import java.io.Serializable;
import java.util.List;

public class Page <T> implements Serializable {

	// 页数（第几页）
    private long currentpage;

	// 查询数据库里面对应的数据有多少条
	private long total;// 从数据库查处的总记录数

	// 每页查5条
	private long size;

	// 下页
	private long next;
	
	private List<T> list;

	// 最后一页
	private long last;
	
	private long lpage;
	
	private long rpage;
	
	//从哪条开始查
	private long start;
	
	//全局偏移量
	public long offsize = 2;
	
	public Page() {
		super();
	}

	public long getCurrentpage() {
		return currentpage;
	}

	/****
	 * 
	 * @param currentpage
	 * @param total
	 * @param pagesize
	 */
	public void setCurrentpage(long currentpage,long total,long pagesize) {
		//可以整除的情况下
		long pagecount =  total/pagesize;
		
		// 4 = 20  / 5
		
		// (4 = 21  /  5) +1
		    
		
		// total%pagesize==0    total/pagesize
		// total%pagesize>0     (total/pagesize)+1
		
		//三目表达式
		// long tpage = total%pagesize==0? total/pagesize:(total/pagesize)+1;
		
		
		//如果整除表示正好分N页，如果不能整除在N页的基础上+1页
		long totalPages = total%pagesize==0? total/pagesize : (total/pagesize)+1;
		
		//总页数
		this.last = totalPages;
		
		//判断当前页是否越界,如果越界，我们就查最后一页
		if(currentpage>totalPages){
			this.currentpage = totalPages;
		}else{
			this.currentpage=currentpage;
		}
		
		//计算start   1----0    2  ------ 5
		this.start = (this.currentpage-1)*pagesize;
		
		//每页显示5条数据
		//1页    0 --  4
		
		//2页    5 --  9
		
		//N页    (当前页-1)*每页显示多少条
		
		// SELECT * FROM TABLE LIMIT #{start},#{end}
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getNext() {
		return  currentpage<last? currentpage+1: last;
	}


	//上一页
	public long getUpper() {
		return currentpage>1? currentpage-1: currentpage;
	}

	public long getLast() {
		return last;
	}

	//总共有多少页，即末页
	public void setLast(long last) {
		this.last = total%size==0? total/size : (total/size)+1;
	}
	

	public long getLpage() {
		return lpage;
	}

	public void setLpage(long lpage) {
		this.lpage = lpage;
	}

	public long getRpage() {
		return rpage;
	}

	public void setRpage(long rpage) {
		this.rpage = rpage;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public void setCurrentpage(long currentpage) {
		this.currentpage = currentpage;
	}

	public void setNext(long next) {
		this.next = next;
	}

	/****
	 * 带有偏移量设置的分页
	 * @param total
	 * @param currentpage
	 * @param pagesize
	 * @param offsize
	 */
	public Page(long total,long currentpage,long pagesize,long offsize) {
		this.offsize = offsize;
		initPage(total, currentpage, pagesize);
	}
	
	/****
	 * 
	 * @param total   总记录数
	 * @param currentpage	当前页
	 * @param pagesize	每页显示多少条
	 */
	public Page(long total,long currentpage,long pagesize) {
		initPage(total,currentpage,pagesize);
	}
	
	/****
	 * 初始化分页
	 * @param total
	 * @param currentpage
	 * @param pagesize
	 */
	public void initPage(long total,long currentpage,long pagesize){
		//总记录数
		this.total = total;
		//每页显示多少条 
		this.size=pagesize;
		
		//计算当前页和数据库查询起始值以及总页数
		setCurrentpage(currentpage, total, pagesize);
		
		//分页计算
		long leftcount =this.offsize,	//需要向上一页执行多少次
			rightcount =this.offsize;
		
		//起点页
		this.lpage =currentpage;
		//结束页
		this.rpage =currentpage;
		
		//2点判断
		this.lpage = currentpage-leftcount;			//正常情况下的起点
		this.rpage = currentpage+rightcount;		//正常情况下的终点
		
		//页差=总页数和结束页的差
		long topdiv = this.last-rpage;				//判断是否大于最大页数
		
		/***
		 * 起点页
		 * 1、页差<0  起点页=起点页+页差值
		 * 2、页差>=0 起点和终点判断
		 */   
		this.lpage=topdiv<0? this.lpage+topdiv:this.lpage;
		
		/***
		 * 结束页
		 * 1、起点页<=0   结束页=|起点页|+1
		 * 2、起点页>0    结束页
		 */
		this.rpage=this.lpage<=0? this.rpage+(this.lpage*-1)+1: this.rpage;
		
		/***
		 * 当起点页<=0  让起点页为第一页
		 * 否则不管
		 */
		this.lpage=this.lpage<=0? 1:this.lpage;
		
		/***
		 * 如果结束页>总页数   结束页=总页数 
		 * 否则不管
		 */
		this.rpage=this.rpage>last? this.last:this.rpage;
	}
	

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	public static void main(String[] args) {
			//总记录数
			//当前页
			//每页显示多少条
			long cpage =17;
			Page page = new Page(1001,cpage,50,7);
			System.out.println("开始页:"+page.getLpage()+"__当前页："+page.getCurrentpage()+"__结束页"+page.getRpage()+"____总页数："+page.getLast());
	}

}
