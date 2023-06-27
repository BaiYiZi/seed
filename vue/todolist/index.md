todolist案例

# 组件化编写流程

1.将静态页面导入
2.实现数据初始化展示
3.交互(功能展示)

# 四大板块 头部 内容区（列表框、列表）底部

在列表区实现数据初始化

list数据放在app.vue下(头部、内容区需要用到list数据，将数据给app父亲组件)

# 1将静态页面导入 

<style scoped></style>

scoped 使样式在局部生效，防止冲突 

# 2实现数据初始化

# 列表框组件(List)

    <Item v-for="listObj in list" :key="listObj.id" :listObj = "listObj"/>

    使用v-for遍历 <Item /> 组件
    定义:key="listObj.id"   key是 一个虚拟Dom对象的标识,当数据发生变化,Vue会根据真实Dom生成新的虚拟Dom,两者进行比较
    定义props 父亲传数据给Item孩子 :listObj = "listObj"

# 列表组件(Item)

    propr:['listObj'] 接收声明对象
    插值语法 获取数据 {{listObj.list数组中的属性}}

    <input type="checkbox" :checked="listObj.done" @click="handleCheck"/>
    复选框实现数据初始化 checked:false / true(勾选框 不选/勾选)

# 3.交互功能实现

# 3.1 App(主页面)

1.实现添加数据功能

methods:{
    //回调、接收孩子的数据 unshift(在数据前方插入数据)
    addTodo(listObj){
        this.list.unshift(listObj)
    }
}

# 3.2 头部功能实现(Header)

<input type="text" placeholder="请输入你的任务名称，按回车键确认" @keyup.enter="add" v-model="content"/>

@keyup.enter 回车 调用add方法
v-model="content" 双向绑定 实时获取输入框内容

data(){
    return{
        //接收输入框数据 content默认为空
        content:''
    }
}

methods:{
    add(){
        if()
        //将用户的输入包装成一个对象（id,title,done）
        const listObj ={id:nanoid(),title:this.content,done:false}
        //通知app组件中addTodo函数添加一个对象
        this.addTodo(listObj)
        //初始化输入框内容
        this.content = ''
    }
}




