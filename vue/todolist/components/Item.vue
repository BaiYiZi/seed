<template>
  <div>
    <li>
      <label>
        <input type="checkbox" :checked="listObj.done" @change="handleCheck(listObj.id)"/>
        <span>{{listObj.title}}</span>
      </label>
      <button class="btn btn-danger" @click="deleteId(listObj.id)">删除</button>
    </li>
  </div>
</template>

<script>
export default {
  name: "Item",
  //声明接收对象
  props:['listObj','checkTodo','deleteTodo'],
  methods:{
    //勾选
    handleCheck(id){
      this.checkTodo(id)
    },
    //删除定义一个方法获取id
    deleteId(id){
      //confirm()弹出是否需要删除
      if(confirm("确定删除吗?")){
      this.deleteTodo(id)
      }
    }
  }
};
</script>

<style scoped>
/*item*/
li {
  list-style: none;
  height: 36px;
  line-height: 36px;
  padding: 0 5px;
  border-bottom: 1px solid #ddd;
}

li label {
  float: left;
  cursor: pointer;
}

li label li input {
  vertical-align: middle;
  margin-right: 6px;
  position: relative;
  top: -1px;
}

li button {
  float: right;
  display: none;
  margin-top: 3px;
}

li:before {
  content: initial;
}

li:last-child {
  border-bottom: none;
}

li:hover{
  background-color: beige;
}
li:hover button{
  display:block;
}
</style>