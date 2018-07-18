
var noApplicationRecord = document.getElementById('noApplicationRecord')

//模拟数据
var data = [
    { business: '万达影院(万胜广场店)', count: '325', num: '20170012565863565656', time: '2017-10-12 16:30', license: '粤A88888' },
    { business: '麦当劳', count: '111', num: '20170012565863565656', time: '2017-10-12 16:30', license: '粤A99999' },
    { business: '肯德基', count: '456', num: '20170012565863565656', time: '2017-10-12 16:30', license: '粤A45466' }
]

var data1=[{ book_name: '《书名书名1》', book_writer: '著者：某某某1', begin_time: '2017-07-12', limit_time: '2017-07-12', name: '姓名：某某某1', student_id: '学号：123456', card_id: '证号：123456' },{ book_name: '《书名书名2》', book_writer: '著者：某某某2', begin_time: '2017-07-12', limit_time: '2017-07-12', name: '姓名：某某某2', student_id: '学号：123456', card_id: '证号：123456' },{ book_name: '《书名书名3》', book_writer: '著者：某某某3', begin_time: '2017-07-12', limit_time: '2017-07-12', name: '姓名：某某某3', student_id: '学号：123456', card_id: '证号：123456' }]

//绘制单个div
function setDiv(item){
    var div = '<div class="body-no-list"><div class="body-no-list-header" ><div class="body-no-list-header-title">'
        + item.business
        + '</div><div class="body-no-list-header-txt">消费金额:&nbsp;'
        + item.count
        + '元<br>消费单号:&nbsp;'
        + item.num
        + '<br>提交时间:&nbsp;'
        + item.time
        + '</div></div><div class="body-no-list-bottom"><div class="body-no-list-bottom-vehicl">'
        + item.license
        + '</div><div><button>撤销</button><button> 修改</button></div></div></div> '
    return div
}

function setDiv1(item){
    var div = '<div class="card"><div class="header" style="background-color: #5BD6B7; border-top-left-radius:10px; border-top-right-radius:10px"><p>借阅详情</p></div><div class="body" style="background-color: #f9f9f9;"><tr><td id="book_name"><span style="color:#333333">'
        + item.book_name
        + '</span></td><td id="book_writer"><span style="color:#333333">'
        + item.book_writer
        + '</span></td></tr><hr/></div><div class="body"><tr><td><span style="color:#6f6f6f">借出时间：</span></td><td id="begin_time"><span style="color:#5BD6B7">'
        + item.begin_time
        + '</span></td></tr></div><div class="body"><tr><td><span style="color:#6f6f6f">应还时间：</span></td><td id="limit_time"><span style="color:#fe5135">'
        + item.limit_time
        + '</span></td></tr></div><div class="body"><p id="name"><span style="color:#6f6f6f">'
        + item.name
        + '</span></p><p id="student_id"><span style="color:#6f6f6f">'
        + item.student_id
        + '</span></p><p id="card_id"><span style="color:#6f6f6f">'
        + item.card_id
        + '</span></p></div></div>'
    return div
}

//循环加载到页面
function getnoApplicationData(){
    var html = ''
    for(var i = 0;i<data1.length;i++){
        html += setDiv1(data1[i])
    }
    noApplicationRecord.innerHTML = html
}

 window.onload = getnoApplicationData()