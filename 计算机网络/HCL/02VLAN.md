[toc]

# VLAN

### access

~~~ shell
# sys
vlan 20  #  创建vlanID
port access vlan 20  #  配置vlanID
~~~

### trunk

~~~ shell
port link-type trunk  #  配置端口链路类型为vlan
port trunk permit vlan all  # 允许指定的vlan通过当前的trunk端口
port trunk pvlan vlan 20  # 设置trunk端口的缺省vlan
~~~

> [!tip]
>
> 主机端口配置为access端口，交换机-交换机或者交换机-路由器之间配置trunk端口

### Hybrid

~~~ shell
port link-type hybrid
port hybrid vlan {vid} {tagged|untagged}
~~~



## 二层交换机

~~~ shell
vlan 10
int g1/0/1
vlan 20 
int g1/0/2
int vlan-interface 10
ip add 192.168.100.254 24
int vlan-interface 20
ip add 192.168.200.254 24
~~~
