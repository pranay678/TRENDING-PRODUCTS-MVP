import Link from 'next/link';

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen py-2">
      <h1 className="text-4xl font-bold mb-8">Welcome to Market Trendz MVP</h1>
      <Link href="/ProductSearchPage">
        <span className="px-6 py-3 bg-blue-600 text-white rounded shadow hover:bg-blue-700 transition cursor-pointer">Go to Product Search</span>
      </Link>
    </div>
  );
}
