添加vercel.json文件

~~~ json
{
  "builds": [
    {
      "src": "src/main.ts",
      "use": "@vercel/node"
    }
  ],
  "routes":[
    {
        "src":"/(.*)",
        "dest":"src/main.ts",
        "methods":["GET","POST","PUT","DELETE"]
    }
  ]
}
~~~

