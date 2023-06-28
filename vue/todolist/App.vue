<template>
  <div id="app">
    <div id="root">
      <div class="todo-container">
        <div class="todo-wrap">
          <Header :addTodo="addTodo" />
          <List :list="list" :checkTodo="checkTodo" :deleteTodo="deleteTodo"/>
          <Footer :list="list" :clearAllTodo="clearAllTodo" :checkAllTodo="checkAllTodo"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// 组件化编码流程
// 1.实现静态页面
// 2.展现动态数据（数据类型、名称，数据保存在哪个组件）
// 3.实现交互-从绑定事件监听开始
import Footer from "@/components/Footer.vue";
import Header from "@/components/Header.vue";
import List from "@/components/List.vue";
export default {
  name: "App",
  components: {
    Footer,
    Header,
    List,
  },
  data() {
    return {
      list: [
        { id: "001", title: "代码", done: true },
        { id: "002", title: "编程", done: false },
        { id: "003", title: "工作", done: true },
      ],
    };
  },
  // 接收
  methods: {
    //添加
    addTodo(listObj) {
      this.list.unshift(listObj);
      // unshift 在数据前方添加
    },
    // 勾选or取消勾选
    checkTodo(id) {
      this.list.forEach((listObj) => {
        if (listObj.id === id) listObj.done = !listObj.done;
      });
    },
    //哪里有数据在哪里写方法 删除
    deleteTodo(id){
      this.list = this.list.filter((listObj)=>{
        return listObj.id !== id
      })
    },
    // 全选or全不选
    checkAllTodo(done){
      this.list.forEach((listObj)=>{
         listObj.done = done
      })
    },
    // 清除所有已完成的选项
    clearAllTodo(){
      this.list = this.list.filter((listObj) =>{
        return !listObj.done
      })
    }
  },
};
</script>

<style scoped>
/*base*/
body {
  background: #fff;
}

.todo-container {
  width: 600px;
  margin: 0 auto;
}
.todo-container .todo-wrap {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}
</style>