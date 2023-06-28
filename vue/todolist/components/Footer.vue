<template>
  <div class="todo-footer" v-show="total">
    <label>
      <input type="checkbox" :checked="isAll" @change="checkAll"/>
    </label>
    <span> <span>已完成{{doneTotal}}</span> / 全部{{total}} </span>
    <button class="btn btn-danger" @click="clearAll">清除已完成任务</button>
  </div>
</template>

<script>
export default {
  name: "Footer",
  props:['list','clearAllTodo','checkAllTodo'],
  computed:{
    // 定义函数 总长度
    total(){
      return this.list.length
    },
    // 定义函数 获取已完成数量
    doneTotal(){
      // 遍历统计 done有几个是true
      let i = 0
      this.list.forEach((listObj) => {
        // 如果查出有完成的任务（done为真） i++累加
        if(listObj.done) i++
      })
      return i
    },
    // 如果总长度 == 已完成的数量 checked属性true
    isAll(){
      return this.total == this.doneTotal && this.total != 0
    },

  },
  methods:{
    checkAll(e){
      this.checkAllTodo(e.target.checked)
    },
    // 调用清除全部事件
    clearAll(){
      this.clearAllTodo()
    }
  },
};
</script>

<style scoped>
/*footer*/
.todo-footer {
  height: 40px;
  line-height: 40px;
  padding-left: 6px;
  margin-top: 5px;
}

.todo-footer label {
  display: inline-block;
  margin-right: 20px;
  cursor: pointer;
}

.todo-footer label input {
  position: relative;
  top: -1px;
  vertical-align: middle;
  margin-right: 5px;
}

.todo-footer button {
  float: right;
  margin-top: 5px;
}

.btn {
  display: inline-block;
  padding: 4px 12px;
  margin-bottom: 0;
  font-size: 14px;
  line-height: 20px;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2),
    0 1px 2px rgba(0, 0, 0, 0.05);
  border-radius: 4px;
}

.btn-danger {
  color: #fff;
  background-color: #da4f49;
  border: 1px solid #bd362f;
}

.btn-danger:hover {
  color: #fff;
  background-color: #bd362f;
}

.btn:focus {
  outline: none;
}

</style>