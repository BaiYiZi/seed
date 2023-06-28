<template>
  <div class="todo-header">
    <input
      type="text"
      placeholder="请输入你的任务名称，按回车键确认"
      v-model="content"
      @keyup.enter="add"
    />
  </div>
</template>

<script>
// nanoid 是函数直接调用即可
import {nanoid} from 'nanoid'
export default {
  name: "Header",
  data() {
    return {
        content:''
    }
  },
  props:['addTodo'],
  methods:{
    add(){
        //效验数据
        if(!this.content.trim()) return alert('输入不能为空！')
        //将用户的输入包装成一个对象
        const listObj ={id:nanoid(),title:this.content,done:false}
        //通知app组件中addTodo函数添加一个对象
         this.addTodo(listObj)
         //清空数据
         this.content = ''
    }
//获取输入的数据方法二 add(event){title:event.target.value}
   
  }
};
</script>

<style scoped>
/*header*/
.todo-header input {
  width: 560px;
  height: 28px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 4px 7px;
}

.todo-header input:focus {
  outline: none;
  border-color: rgba(82, 168, 236, 0.8);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(82, 168, 236, 0.6);
}
</style>