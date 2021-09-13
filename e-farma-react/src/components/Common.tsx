export class Common {
    public static readonly URL = "http://localhost:9000";
    public static readonly ID = document.cookie.split(";").filter((value, index) => value.includes("Id")).map(value => value.split("=")[1]).toString();
}