const template = document.createElement("template")

template.innerHTML = `<div>

<slot name='decrement'></slot>
<span> Count : <span data-counter> </span> </span>
<slot name='incrementButton'></slot>

</div>`

class Counter extends HTMLElement{
    static get observedAttributes(){
        return ["data-count"]
    }
    constructor(){
        super();
        this.shadow = this.attachShadow({mode:"open"});
        this.shadowRoot.append(template.content.cloneNode(true));
        this._count = this.getAttribute("data-count") || 0;
        const counter = this.shadowRoot.querySelector("[data-counter]")
        counter.innerHTML = this._count;
        this.shadowRoot.querySelector("slot[name='incrementButton']").addEventListener("click",()=>{
            console.log("incrementButton clicked")
            this._count++;
            this.setAttribute("data-count",this._count)
        })
        this.shadowRoot.querySelector("slot[name='decrement']").addEventListener("click",()=>{
            console.log("decrement clicked")
            this._count--;
            this.setAttribute("data-count",this._count)
            // counter.innerHTML = this._count;
        })
    }
    attributeChangedCallback(name,oldValue,newValue){
        if(name === "data-count"){
            console.log("attributeChangedCallback",name,oldValue,newValue)
            this._count = newValue;
            this.shadowRoot.querySelector("[data-counter]").innerHTML = this._count;
        }
    }
}

customElements.define("custom-counter",Counter)