# toRef和toRefs

创建一个ref对象，其value值指向另一个对象中的某个属性

语法：`const name = toRef(person,'name')`

应用：要将响应式对象中的某个属性单独提供给外部使用时。

扩展：`toRefs`与`toRef`功能一致，但可以批量创建多个ref对象，语法：toRefs(person)

~~~ js
let person = reactive({
    name:'张三',
    age:18,
    job:{
        j1:{
            salary:20
        }
    }
})
const name = toRef(person,'name');
return{
    person,
    name:toRef(person,'name'),
    age:toRef(person,'age'),
    salary:toRef(person.job.j1,'salary')
}
/*                toRefs                */
return{
    person,
    ...toRefs(person)
}
~~~

